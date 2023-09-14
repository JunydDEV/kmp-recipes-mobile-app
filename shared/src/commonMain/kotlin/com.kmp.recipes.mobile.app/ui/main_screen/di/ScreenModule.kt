import com.kmp.recipes.mobile.app.ui.main_screen.MainScreenModel
import org.koin.dsl.module

val mainScreenModule = module {
    factory { MainScreenModel(repository = get()) }
}