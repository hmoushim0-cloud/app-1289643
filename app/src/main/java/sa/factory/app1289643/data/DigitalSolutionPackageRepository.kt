package sa.factory.app1289643.data

import sa.factory.app1289643.model.DigitalSolutionPackage
import sa.factory.app1289643.model.ServiceFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface DigitalSolutionPackageRepository {
    fun getAvailableCatalog(): Flow<List<ServiceFeature>>
    fun getActiveRequest(): Flow<DigitalSolutionPackage?>
    suspend fun submitRequest(request: DigitalSolutionPackage): Result<String>
}

class InMemoryDigitalSolutionPackageRepository : DigitalSolutionPackageRepository {
    private val _catalog = MutableStateFlow<List<ServiceFeature>>(listOf(
        ServiceFeature("1", "تصميم موقع إلكتروني", "تصميم موقع احترافي مع استجابة للهواتف وتكامل مع أنظمة إدارة المحتوى", 5000L, "verified"),
        ServiceFeature("2", "تطوير منصة رقمية", "منصة رقمية مخصصة لإدارة المشاريع والعمليات مع واجهة سهلة الاستخدام", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<DigitalSolutionPackage?>(null)

    override fun getAvailableCatalog(): Flow<List<ServiceFeature>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<DigitalSolutionPackage?> = _active.asStateFlow()

    override suspend fun submitRequest(request: DigitalSolutionPackage): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
