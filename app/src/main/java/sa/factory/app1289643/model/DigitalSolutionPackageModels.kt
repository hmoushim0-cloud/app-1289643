package sa.factory.app1289643.model

import kotlinx.serialization.Serializable

@Serializable
data class ServiceFeature(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class DigitalSolutionPackage(
    val requestId: String,
    val items: List<ServiceFeature>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: DigitalSolutionPackageStatus = DigitalSolutionPackageStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class DigitalSolutionPackageStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
