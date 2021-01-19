package com.anime.dl.extensions

import android.content.Context
import androidx.preference.PreferenceManager
import com.anime.dl.R
import com.anime.dl.extensions.models.Extension

class ExtensionManager(private val context: Context) {

    var installedExtensions = mutableListOf<Extension.Installed>()
    var availableExtensions = mutableListOf<Extension.Available>()
    var prefs = PreferenceManager.getDefaultSharedPreferences(context)

    init {
        initExtensions()
    }

    private fun initTutorialExtension() {
        if (prefs.getBoolean("tutorial_installed", false)) {
            installedExtensions
                .add(
                    Extension.Installed(
                        name = context.resources!!.getString(R.string.tutorial_extension),
                        pkgName = "com.anime.dl.tutorial",
                        lang = "all",
                        versionName = "0.0",
                        versionCode = 1,
                        isTutorial = true
                    )
                )
        } else {
            availableExtensions
                .add(
                    Extension.Available(
                        name = context.resources!!.getString(R.string.tutorial_extension),
                        pkgName = "com.anime.dl.tutorial",
                        lang = "all",
                        versionName = "0.0",
                        versionCode = 1,
                        isTutorial = true
                    )
                )
        }
    }

    private fun initExtensions() {
        if (!prefs.getBoolean("tutorial_complete", false)) {
            initTutorialExtension()
        }
    }

    public fun installExtension(extension: Extension) {
        if (extension.isTutorial) {
            prefs.edit().putBoolean("tutorial_installed", true).apply()
        }
    }
}
