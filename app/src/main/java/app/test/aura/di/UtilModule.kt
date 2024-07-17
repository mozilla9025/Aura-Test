package app.test.aura.di

import app.test.aura.utils.CoroutineDispatchers
import app.test.aura.utils.DefaultDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UtilModule {

    @Binds
    fun bindCoroutineDispatchers(impl: DefaultDispatchers): CoroutineDispatchers
}