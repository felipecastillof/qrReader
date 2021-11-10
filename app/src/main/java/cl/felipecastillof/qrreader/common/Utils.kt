package cl.felipecastillof.qrreader.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import cl.felipecastillof.qrreader.MainApplication
import java.io.*

class Utils {

    companion object{

        fun leeConf(
            campo: String,
            default_valor: String
        ): String {
            val prefs =
                MainApplication.applicationContext().getSharedPreferences(Const.PREF_NOMBRE, Context.MODE_PRIVATE)
            return prefs.getString(campo, default_valor)!!
        }


        fun escribeConf(
            campo: String?,
            valor: String?
        ) {

            val editor =
                MainApplication.applicationContext().getSharedPreferences(Const.PREF_NOMBRE, Context.MODE_PRIVATE).edit()
            editor.putString(campo, valor)
            editor.commit()
        }

        fun drawableToBase64( bitmap: Bitmap): String {
            val c:Context=MainApplication.applicationContext()
            //Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(),R.drawable.borde);
            val byteStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
            val byteArray = byteStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }


        fun drawableToBitmap(drawable: Drawable): Bitmap? {
            var bitmap: Bitmap? = null
            if (drawable is BitmapDrawable) {
                val bitmapDrawable = drawable
                if (bitmapDrawable.bitmap != null) {
                    return bitmapDrawable.bitmap
                }
            }
            var canvas:Canvas?=null
            if (drawable != null) {
                bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
                    Bitmap.createBitmap(
                        1,
                        1,
                        Bitmap.Config.ARGB_8888
                    ) // Single color bitmap will be created of 1x1 pixel
                } else {
                    Bitmap.createBitmap(
                        drawable!!.intrinsicWidth,
                        drawable.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    )
                }
                canvas = Canvas(bitmap)
            }
            drawable?.setBounds(0, 0, canvas!!.width, canvas.height)
            drawable?.draw(canvas!!)
            return bitmap
        }


        fun comprimeBitmap(image: Bitmap, maxSize: Int): Bitmap? {
            Log.e("ppl Original dimensions", image.width.toString() + " " + image.height)
            Log.d("ppl Original weight", "Antes: " + image.byteCount.toString())
            var width = image.width
            var height = image.height
            val bitmapRatio = width.toFloat() / height.toFloat()
            if (bitmapRatio > 1) {
                width = maxSize
                height = (width / bitmapRatio).toInt()
            } else {
                height = maxSize
                width = (height * bitmapRatio).toInt()
            }
            val out = ByteArrayOutputStream()

            //Esto comprime la imagen
            image.compress(Bitmap.CompressFormat.PNG, 100, out)
            val decoded = BitmapFactory.decodeStream(ByteArrayInputStream(out.toByteArray()))
            Log.d("ppl", "Despues: " + decoded.byteCount.toString())
            Log.e("Compressed dimensions", decoded.width.toString() + " " + decoded.height)
            return Bitmap.createScaledBitmap(decoded, width, height, true)
        }


    }



}