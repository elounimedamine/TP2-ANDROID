package com.example.chatinterfacefinale

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatinterfacefinale.ui.theme.ChatInterfaceFinaleTheme

//ajouter un text dans l'interface principale
//faire appel à cette fonction dans la fonction onCreate
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //importer une listes de messages importer cette liste dans un fichier kt dans le projet sampleData
            Conversation(SampleData.conversationSample)
        }
    }
}

//definir la classe data Message
data class Message(val author: String, val body: String)

//definir une fonction composable **
//Pour utiliser plusieurs compose dans l´interface graphique, il faut définir un layout pour organiser le compose (utiliser Row ou Column)
//ajouter un layout Column pour placer les 2 text
//ajouter une image dans un layout row
@Composable
fun MessageCard(name: String) {
    Row() {
        Image(painter = painterResource(id = R.drawable.profile),
            contentDescription ="Profile image" )
        Column(){
            Text(text = "Hello $name!")
            Text(text = "Hello $name!")

        }
    }

}

//redefinir MessageCard
//ajout d un padding autour de chaque ligne message
//ajouter un espace vertical entre les texts
//ajouter un espace horizontal
//utiliser le theme par defaut créé au nom de l´application : changer les couleurs
//changer les typographies
//formes
@Composable
fun MessageCard(message: Message) {
    Row(modifier=Modifier.padding(all=8.dp)) {
        Image(painter = painterResource(id = R.drawable.profile), //path
            contentDescription ="Profile image",
            modifier=Modifier.size(40.dp) //taille
                .clip(shape= CircleShape) //le cercle
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape) //le design du cercle en vert

        )
        Spacer(modifier = Modifier.width(8.dp)) //un espacement

        Column(){
            Text(text = message.author, //nom du envoyeur
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = message.body, //le contenu du message envoyé
                style = MaterialTheme.typography.body2
            )

            //la conversation qui est en white
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }

}
//definir une fonction qui affiche une listes de messages
@Composable
fun Conversation(messages: List<Message>) {
    //Lists
    LazyColumn {
        items(messages) { message ->
            MessageCard(message) //appel à la méthode MessageCard(message)
        }
    }
}

//Pour voir le preview de votre code avant execution ajouter l'annotaion @Preview á la fonction DefaultPreview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatInterfaceFinaleTheme {
        //importer une listes de messages importer cette liste dans un fichier kt dans le projet sampleData
        Conversation(SampleData.conversationSample)
    }
}