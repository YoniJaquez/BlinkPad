package com.nocountry.blinkpad.view.utils

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FacebookLoginHandler{

    private var _callback = mutableStateOf<(@Composable () -> Unit)?>(null)

    suspend fun getContent(
        loginManager: LoginManager,
        callbackManager: CallbackManager,
        listOf: List<String> = listOf(),
        maxTry: Int = 10,
        millis: Long = 200,
    ): AccessToken? {
        return request(
            contract = loginManager.createLogInActivityResultContract(callbackManager, null),
            loginManager = loginManager,
            callbackManager = callbackManager,
            maxTry,
            millis
        ) { launcher ->
            launcher.launch(listOf)
        }
    }

    suspend fun request(
        contract: ActivityResultContract<Collection<String>, CallbackManager.ActivityResultParameters>,
        loginManager: LoginManager,
        callbackManager: CallbackManager,
        maxTry: Int = 10,
        millis: Long = 200,
        launcher: (ManagedActivityResultLauncher<Collection<String>, CallbackManager.ActivityResultParameters>) -> Unit,
    ): AccessToken? = suspendCancellableCoroutine { coroutine ->
        _callback.value = {
            val a = rememberLauncherForActivityResult(
                contract
            ) {
                // nothing specific to do here, handled in FacebookCallback
                _callback.value = null
                return@rememberLauncherForActivityResult
            }

            LaunchedEffect(a) {
                var tried = 0
                var tryOn = true
                while (tryOn) {
                    ++tried
                    delay(millis)
                    try {
                        launcher(a)
                        tryOn = false
                    } catch (e: Exception) {
                        if (tried > maxTry) {
                            tryOn = false
                            coroutine.resume(null)
                            _callback.value = null
                        }
                    }
                }
            }

            DisposableEffect(Unit) {
                loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onCancel() {
                        // do nothing
                    }

                    override fun onError(error: FacebookException) {
                        error.printStackTrace()
                    }

                    override fun onSuccess(result: LoginResult) {
                        coroutine.resume(result.accessToken)
                    }
                })

                onDispose {
                    loginManager.unregisterCallback(callbackManager)
                }
            }
        }
    }

    @Composable
    fun handle() {
        if (_callback.value != null) {
            _callback.value?.invoke()
        }
    }
}
