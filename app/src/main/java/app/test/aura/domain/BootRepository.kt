package app.test.aura.domain

import app.test.aura.data.BootDao
import app.test.aura.data.model.BootEntity
import app.test.aura.domain.model.BootModel
import app.test.aura.utils.CoroutineDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO Migrate the repository to be a interface with the implementation and couple them together using the @Binds annotation
class BootRepository @Inject constructor(
    private val bootDao: BootDao,
    private val coroutineDispatchers: CoroutineDispatchers
) {
    suspend fun saveBootTime(bootTimeStamp: Long) {
        withContext(coroutineDispatchers.io) {
            bootDao.insert(
                BootEntity(timeStamp = bootTimeStamp)
            )
        }
    }

    suspend fun readBoots(): List<BootModel> {
        return withContext(coroutineDispatchers.io) {
            bootDao.getAll().map(BootModel::from)
        }
    }
}