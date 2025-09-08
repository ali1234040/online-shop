package com.example.firstapplication.ui.screen


import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firstapplication.R
import com.example.firstapplication.data.remote.RetrofitService.apiService
import com.example.firstapplication.data.remote.safeGetData
import com.example.firstapplication.utils.Height
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun FeedbackScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = { ScaffoldTopBar(navController) },
    ) { padding ->
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.light_cream))
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ){
            Column {
                Spacer(modifier = Modifier.height(padding.calculateTopPadding()))
                FeedbackContent()
            }
        }
    }
}

@Composable
fun FeedbackContent() {

    val feedbackText = remember { mutableStateOf("") }
    val feedbackMessage = remember { mutableStateListOf<String>() }
    var isLoading by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.light_cream)),
        contentAlignment = Alignment.Center

    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 270f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(-size.width / 2, size.height / 2),
                    size = Size(size.width, size.height)
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 90f,
                    sweepAngle = 90f,
                    useCenter = true,
                    topLeft = Offset(size.width / 2, -size.height / 2),
                    size = Size(size.width, size.height)
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Canvas(
                modifier = Modifier
                    .size(height = 300.dp, width = 200.dp)
                    .background(
                        colorResource(R.color.light_cream)
                    )
            ) {
                drawArc(
                    color = Color(0xFFFCEDDF),
                    startAngle = 270f,
                    sweepAngle = 180f,
                    useCenter = true,
                    topLeft = Offset(-size.width / 4, size.height - size.width / 2),
                    size = Size(size.width / 2, size.width / 2)
                )
            }
        }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {

                feedbackMessage.forEach {
                    ChatText(it)
                }
                Height(10)

                var isFocused by remember { mutableStateOf(true) }
                val coroutineScope = rememberCoroutineScope()


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(end = 14.dp)
                ) {
                    TextField(
                        value = feedbackText.value,
                        onValueChange = { feedbackText.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused
                            },
                        textStyle = TextStyle(
                            textDirection = TextDirection.Rtl
                        ),
                        placeholder = {
                            Text(
                                text = "text",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    textDirection = TextDirection.Rtl
                                )
                            )
                        },
                        trailingIcon = if (isFocused) {
                            {
                                IconButton(

                                    onClick = {
                                        if (feedbackText.value != "") feedbackMessage.add(
                                            feedbackText.value
                                        )
                                        coroutineScope.launch {

                                            val delayJob = launch {
                                                delay(1000)
                                                isLoading = true
                                            }
                                            safeGetData { apiService.sendMessage(text = feedbackText.value) }
                                            delayJob.cancel()
                                            isLoading = false
                                            feedbackText.value = ""
                                        }
                                    }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Send,
                                        contentDescription = "search Icon",
                                        tint = Color.Unspecified,
                                    )
                                }
                            }
                        } else null,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            cursorColor = colorResource(R.color.dark_orange)
                        )
                    )
                }
            }
        if (isLoading)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CustomCircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = Color.Blue,
                    strokeWidth = 4.dp,
                    rotationSpeed = 180f
                )
            }
    }
}

@Composable
fun ChatText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .height(40.dp)
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.light_orange),
                        colorResource(id = R.color.dark_orange)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(6.dp),
        fontSize = 14.sp
    )
}

//برای تغییر سرعت چرخش پروگرس بار
@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Dp = 4.dp,
    rotationSpeed: Float = 360f
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (360 / rotationSpeed * 1000).toInt(),
                easing = CubicBezierEasing(0.2f, 0.1f, 0.68f, 0.92f)            )
        )
    )

    Canvas(modifier = modifier) {
        rotate(angle) {
            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}

