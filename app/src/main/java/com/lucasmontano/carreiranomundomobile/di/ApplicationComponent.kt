import android.app.Application
import com.lucasmontano.carreiranomundomobile.collections.di.CollectionsModule
import com.lucasmontano.carreiranomundomobile.di.RepositoryModule
import com.lucasmontano.carreiranomundomobile.di.RoomModule
import com.lucasmontano.carreiranomundomobile.form.HabitFormFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    RoomModule::class,
    RepositoryModule::class,
    CollectionsModule::class
  ]
)
interface ApplicationComponent {

  fun inject(formFragment: HabitFormFragment)

  @Component.Factory
  interface Factory {

    fun create(@BindsInstance app: Application): ApplicationComponent
  }
}
