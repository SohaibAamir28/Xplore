package com.example.xplore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.xplore.ui.theme.XploreTheme
import kotlinx.coroutines.launch

data class XRDesign(
    val id: Int,
    val name: String,
    val description: String
)

class HomeActivity : ComponentActivity() {

    private val xrDesigns = listOf(
        XRDesign(1, "AR Virtual Tour", "A guided virtual tour using AR."),
        XRDesign(2, "VR Gaming Interface", "An immersive gaming interface."),
        XRDesign(3, "Interactive Learning", "An XR app for educational purposes.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XploreTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(
                        xrDesigns = xrDesigns,
                        onDesignClick = { design ->
                            val intent = Intent(this, DetailActivity::class.java).apply {
                                putExtra("design_id", design.id)
                                putExtra("design_name", design.name)
                                putExtra("design_description", design.description)
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    xrDesigns: List<XRDesign>,
    onDesignClick: (XRDesign) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Xplore - XR Designs") })
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(xrDesigns) { design ->
                    XRDesignCard(design, onDesignClick)
                }
            }
        }
    )
}

@Composable
fun XRDesignCard(
    design: XRDesign,
    onDesignClick: (XRDesign) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onDesignClick(design) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = design.name,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = design.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )
        }
    }
}
