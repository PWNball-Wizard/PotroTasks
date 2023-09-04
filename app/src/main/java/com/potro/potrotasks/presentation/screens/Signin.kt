
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.R
import com.potro.potrotasks.navigation.AppScreens

@Composable
fun SignInView(navController: NavController){
    val token = stringResource(R.string.default_web_client_id)//Will be generated after Firebase Integration
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->//Here, we get the Intent result
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            Firebase.auth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        try {
                            navController.navigate(AppScreens.HomeActivity.route)
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        try {
                            throw it.exception!!
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }

        } catch (e: ApiException) {
            Log.e("TAG", "Google sign in failed", e)
        }
    }
    Button(
        onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()//Creating GSO object
             //<- Outside of This button composition
            val googleSignInClient = GoogleSignIn.getClient(context, gso)//Get client obj
            launcher.launch(googleSignInClient.signInIntent)//Launch the intent using a launcher that we will create now.
        },
        content = {
            Text(text = "Sign In")
        }
    )
}