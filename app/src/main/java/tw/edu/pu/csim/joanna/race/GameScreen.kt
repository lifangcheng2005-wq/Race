package tw.edu.pu.csim.joanna.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String,gameViewModel: GameViewModel) {
    //載入圖片
    //val imageBitmap = ImageBitmap.imageResource(R.drawable.horse0)

    val imageBitmaps = listOf(
        ImageBitmap.imageResource(R.drawable.horse0),
        ImageBitmap.imageResource(R.drawable.horse1),
        ImageBitmap.imageResource(R.drawable.horse2),
        ImageBitmap.imageResource(R.drawable.horse3)
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume() // 告訴系統已經處理了這個事件
                        gameViewModel.MoveCircle(dragAmount.x, dragAmount.y)
                    }
                }

        ) {
            // 繪製圓形
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )
            for (i in 0..2) {
                drawImage(
                    image = imageBitmaps[gameViewModel.horses[i].numberNo],
                    dstOffset = IntOffset(
                        gameViewModel.horses[i].horseX,
                        gameViewModel.horses[i].horseY
                    ),
                    dstSize = IntSize(200, 200)
                )

            }

        }

            Text(
                text = "分數：${gameViewModel.score}",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            Text(
                text = message + gameViewModel.screenWidthPx.toString() + "*"
                        + gameViewModel.screenHeightPx.toString(),
                modifier = Modifier.align(Alignment.TopStart)//置於左上角
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = {
                    if (!gameViewModel.gameRunning) {
                        gameViewModel.gameRunning = true
                        gameViewModel.StartGame()

                    }
                }
                ) {
                    Text("遊戲開始")
                }

            }
        }
    }