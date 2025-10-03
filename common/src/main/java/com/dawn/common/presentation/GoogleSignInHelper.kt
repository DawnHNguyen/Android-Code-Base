package com.dawn.common.presentation

import android.content.Context
import android.credentials.CreateCredentialException.TYPE_USER_CANCELED
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.dawn.common.R
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlin.coroutines.cancellation.CancellationException

class GoogleSignInHelper(
    private val context: Context
) {

    private var credentialRequest: GetCredentialRequest? = null

    fun initGoogleSSO() {
        if (credentialRequest != null) return
        val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption
            .Builder(context.getString(R.string.default_web_client_id))
            .build()
        credentialRequest = GetCredentialRequest.Builder()
            .setPreferImmediatelyAvailableCredentials(false)
            .setPreferIdentityDocUi(true)
            .addCredentialOption(signInWithGoogleOption)
            .build()
    }


    private inline fun handleSignIn(
        result: GetCredentialResponse,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                try {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken
                    onSuccess(idToken)
                } catch (e: GoogleIdTokenParsingException) {
                    onError("GoogleSignInHelper - handleSignIn - CustomCredential - catch: msg = ${e.message}}")
                }
            }
            else -> {
                onError("GoogleSignInHelper - handleSignIn - else: credential = ${credential}}")
            }
        }
    }

    suspend fun googleSignIn(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
        onCancel: () -> Unit,
    ) {
        try {
            val credentialManager = CredentialManager.create(context)

            val request = credentialRequest
                ?: throw IllegalStateException("Credential request not initialized. Call initGoogleSSO first.")

            val result = credentialManager.getCredential(
                request = request,
                context = context,
            )

            handleSignIn(
                result = result,
                onSuccess = onSuccess,
                onError = onError
            )
        } catch (e: GetCredentialException) {
            if (e.type != TYPE_USER_CANCELED) {
                onError("GoogleSignInHelper - googleSignIn - catch GetCredentialException: type = ${e.type}, msg = ${e.message}}")
            } else onCancel()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            onError("GoogleSignInHelper - googleSignIn - catch: msg = $e")
        }
    }
}