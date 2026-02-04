package chat.stoat.ndk

import android.util.Log
import chat.stoat.BuildConfig

annotation class NativeLibrary(val name: String) {
    companion object {
        const val LIB_NAME_NATIVE_MARKDOWN = "stendal"
        const val LIB_NAME_NATIVE_MARKDOWN_V2 = "finalmarkdown"
    }
}

object NativeLibraries {
    private const val TAG = "NativeLibraries"
    var finalMarkdownAvailable: Boolean = false
        private set

    fun init() {
        System.loadLibrary(NativeLibrary.LIB_NAME_NATIVE_MARKDOWN)
        Stendal.init()

        try {
            System.loadLibrary(NativeLibrary.LIB_NAME_NATIVE_MARKDOWN_V2)
            FinalMarkdown.init(BuildConfig.DEBUG)
            finalMarkdownAvailable = true
        } catch (e: UnsatisfiedLinkError) {
            Log.w(TAG, "FinalMarkdown native library not available; skipping init.", e)
        }
    }
}
