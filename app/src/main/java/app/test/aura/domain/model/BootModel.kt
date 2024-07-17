package app.test.aura.domain.model

import app.test.aura.data.model.BootEntity

data class BootModel(
    val id: Int,
    val timeStamp: Long
) {
    companion object {
        fun from(bootEntity: BootEntity): BootModel {
            return BootModel(bootEntity.id, bootEntity.timeStamp)
        }
    }
}