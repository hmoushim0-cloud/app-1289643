package sa.factory.app1289643.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289643.data.DigitalSolutionPackageRepository
import sa.factory.app1289643.model.DigitalSolutionPackage
import sa.factory.app1289643.model.ServiceFeature
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DigitalSolutionPackageViewModel(
    private val repository: DigitalSolutionPackageRepository
) : ViewModel() {
    val catalog: StateFlow<List<ServiceFeature>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<DigitalSolutionPackage?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: DigitalSolutionPackage) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
