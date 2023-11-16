package com.potro.potrotasks.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.potro.potrotasks.R
import com.potro.potrotasks.domain.model.profile.ProfileViewModel
import io.grpc.okhttp.internal.framed.Header

@ExperimentalMaterial3Api
@Composable
    fun ProfileScreen(navController: NavController
    /*viewModel: ProfileViewModel = hiltViewModel(),*/
    ) {
    val user = FirebaseAuth.getInstance().currentUser
    val name = user?.displayName ?: "Nombre no disponible"
    val email = user?.email ?: "Email no disponible"
    val photoUrl = user?.photoUrl

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Alinea los hijos horizontalmente al centro
        verticalArrangement = Arrangement.Center, // Alinea los hijos verticalmente al centro
        modifier = Modifier.fillMaxSize() // Ocupa todo el espacio disponible
    ) {
        Image(
            painter = rememberImagePainter(data = photoUrl),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(text = name)
        Text(text = email)

        // Botón para cerrar sesión
        Button(onClick = {
            FirebaseAuth.getInstance().signOut() // Cierra la sesión en Firebase
            navController.navigate("signin_screen") { // Navega a la pantalla de login
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
            , colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF444444))
        ) {
            Text("Cerrar Sesión")
        }
    }

    /*
        val state by viewModel.state.collectAsState()
        ProfileContent(
            state = state,
            onChangeFirstName = viewModel::onChangeFirstName,
            onChangeLastName = viewModel::onChangeLastName,
            onChangeLocation = viewModel::onChangeLocation,
            onChangeEmail = viewModel::onChangeEmail,
            onChangePhone = viewModel::onChangePhone,
            onSaveUserInfo = viewModel::onSaveUserInfo
        )
    }

    @Composable
    private fun ProfileContent(
        state: ProfileUiState,
        onChangeFirstName: (String) -> Unit,
        onChangeLastName: (String) -> Unit,
        onChangeLocation: (String) -> Unit,
        onChangeEmail: (String) -> Unit,
        onChangePhone: (String) -> Unit,
        onSaveUserInfo: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                title = stringResource(R.string.app_name),
                subtitle = stringResource(R.string.app_name),
            )
            SpaceVertical32()

            ProfileAvatar(
                painter = rememberAsyncImagePainter(model = state.profilePictureLink),
                size = 128
            )
            SpaceVertical24()

            TextButton(text = stringResource(R.string.change_profile_picture)) {}
            SpaceVertical32()

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1F)) {
                    InformationCard(
                        title = stringResource(R.string.first_name),
                        information = state.firstName,
                        onTextChange = onChangeFirstName
                    )
                }
                SpaceHorizontal16()
                Box(modifier = Modifier.weight(1F)) {
                    InformationCard(
                        title = stringResource(R.string.second_name),
                        information = state.lastName,
                        onTextChange = onChangeLastName
                    )
                }
            }
            InformationCard(
                title = stringResource(R.string.location),
                information = state.location,
                onTextChange = onChangeLocation
            )
            InformationCard(
                title = stringResource(R.string.email),
                information = state.email,
                onTextChange = onChangeEmail
            )
            InformationCard(
                title = stringResource(R.string.phone),
                information = state.phone,
                onTextChange = onChangePhone
            )

            Spacer(modifier = Modifier.weight(1F))
            DefaultButton(buttonText = stringResource(R.string.save), onClick = onSaveUserInfo)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewProfileScreen() {
        ProfileScreen()
    }*/
}

