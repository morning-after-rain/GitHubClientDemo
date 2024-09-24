package com.ken.githubclient.utls

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.ken.githubclient.GithubApplication
import com.ken.githubclient.R

object LanguageToColor {
    public fun getLanguageColor(language: String?): ColorDrawable {
        if (language == null) return ColorDrawable(Color.TRANSPARENT)

        val colorProvider: (Int) -> ColorDrawable = { resId ->
            ColorDrawable(ContextCompat.getColor(GithubApplication.instance, resId))
        }

        return colorProvider(
            when (language) {
                "Kotlin" -> R.color.color_language_kotlin
                "Java" -> R.color.color_language_java
                "JavaScript" -> R.color.color_language_js
                "Python" -> R.color.color_language_python
                "HTML" -> R.color.color_language_html
                "CSS" -> R.color.color_language_css
                "Shell" -> R.color.color_language_shell
                "C++" -> R.color.color_language_cplus
                "C" -> R.color.color_language_c
                "ruby" -> R.color.color_language_ruby
                else -> R.color.color_language_other
            }
        )
    }
}