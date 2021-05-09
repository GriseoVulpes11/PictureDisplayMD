

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.Window
import androidx.compose.desktop.WindowEvents
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import java.awt.SystemColor.window
import java.io.File
import javax.swing.text.View
import java.util.Timer
import java.util.logging.Logger.global
import kotlin.concurrent.schedule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)

fun main() =
    Window(size = IntSize(1440, 900),undecorated = false) {
        AppManager.focusedWindow?.makeFullscreen()
        var file = listOf<String>("images/index.jpg", "images/index1.jpg", "images/index2.jpg", "images/index3.jpg")

        val padding = 100
        val colors = MaterialTheme.colors
        val scope = rememberCoroutineScope()
        val state = rememberLazyListState()

        MaterialTheme() {

            @Composable
            fun DisplayImage() {
                LazyColumn(modifier = Modifier.padding(padding.dp), state = state) {
                    items(file.size) {


                        val firstVisibleItemScrollOffset: Int
                        Image(
                            bitmap = imageResource(File(file[it]).toString()),
                            contentDescription = "Image",
                            contentScale = ContentScale.Fit
                        )

                    }
                }
                scope.launch {
                    // Animate scroll to item with index=5
                    state.animateScrollToItem(index = 3)
                }
                var i = 0
                var time_wait = true

                while (time_wait) {
                    val currentTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("ss")
                    val formatted = currentTime.format(formatter)
                    fun timedEvent(item: Int) {

                        if (currentTime.toString() == "00" || currentTime.toString() == "30") {
                            scope.launch {
                                state.animateScrollToItem(index = item)
                            }
                        }
                    }
                    timedEvent(i)

                    if (i >= file.size) {
                        i = 0
                    } else {
                        i++
                    }

                }
            }



            Column(modifier = Modifier.padding(padding.dp).fillMaxWidth().fillMaxHeight()) {
                Text("Happy Mothers Day!", fontSize = 40.sp,textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                Text("  ", fontSize = 50.sp)
                Text("This is a custom made computer and screen for you!", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)
                Text("Dylan made the Hardware, while I am making  the software", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)
                Text("We hope you have a great day!", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)
                Text("-Cooper", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 90.sp)
                Text("Hey mom!", fontSize = 40.sp,textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                Text("  ", fontSize = 30.sp)
                Text("I hope you have a fantastic day!", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)
                Text("Enjoy this gift from cooper and I", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)
                Text("-Dylan", fontSize = 30.sp,textAlign = TextAlign.Center)
                Text("  ", fontSize = 30.sp)


            }

        }
    }



















