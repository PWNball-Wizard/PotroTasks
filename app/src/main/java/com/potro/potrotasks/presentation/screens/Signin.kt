
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.potro.potrotasks.R
import com.potro.potrotasks.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E5E5))
    ) {
        // Primer Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(PaddingValues(top = 100.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            AppLogo()
            AppTitle()
        }

        // Segunda Columna
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(top = 50.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VectorArt()
            AppDescription()
        }

        // Botón de Inicio de Sesión con Google
        SignInWithGoogleButton(navController)
    }
}

@Composable
fun AppDescription() {
    Text(modifier = Modifier
        .width(260.dp),
        textAlign = TextAlign.Center ,
        //fontFamily = interLight,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        text = "TIANGUISTENCO, MÉXICO"//stringResource(R.string.app_description)
    )
}

@Composable
fun AppLogo() {
    Box(modifier = Modifier
        .shadow(10.dp, RoundedCornerShape(20.dp), true)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .size(120.dp),
        contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource( id = R.drawable.uaemex),
            contentDescription = "",
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun AppTitle() {
    Text(modifier = Modifier.padding(PaddingValues(start = 20.dp, top = 20.dp)),
        text = buildAnnotatedString {
            append("Bienvenido a ")
            withStyle(style = SpanStyle(
                fontWeight = FontWeight.Bold,
                //fontFamily = interBold,
                fontSize = 28.sp
            )
            ) {
                append("\nPotroTasks")
            }
        },
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        //fontFamily = interLight,
        color = DarkGray
    )
}

@Composable
fun SignInWithGoogleButton(navController: NavController) {
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    // Crear el launcher para el resultado de la actividad de inicio de sesión de Google
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        task.addOnCompleteListener { signInTask ->
            if (signInTask.isSuccessful) {
                // Inicio de sesión exitoso, obtener el token y navegar a la pantalla principal
                val account = signInTask.result
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                Firebase.auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        navController.navigate("main_screen")
                    } else {
                        Toast.makeText(context, "Authentication Failed: ${authTask.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Inicio de sesión fallido, mostrar un mensaje de error
                Toast.makeText(context, "Ingresa con Google: ${signInTask.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .shadow(12.dp, RoundedCornerShape(10.dp), true)
                    .clip(RoundedCornerShape(10.dp))
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = DarkGray
                ),
                onClick = {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                }
            ) {
                Text(text = "Continuar con Google")
            }
        }
    }
}


@Composable
fun VectorArt() {
    Image(
        painter = painterResource( id = R.drawable.potro_login),
        contentDescription = "",
        modifier = Modifier
            .width(280.dp)
            .height(230.dp),
    )
}

    /*val token = stringResource(R.string.default_web_client_id)
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
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)//Get client obj
            launcher.launch(googleSignInClient.signInIntent)//Launch the intent using a launcher that we will create now.
        },
        content = {
            Text(text = "Sign In")
        }
    )
}*/