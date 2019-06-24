package com.carbontower.domain.services.machine

enum class MetricsMachine(val metric: String) {
    tempCPU("Temperatura CPU"),
    tempGPU("Temperatura GPU"),
    useCPU("Uso CPU"),
    useGPU("Uso GPU"),
    useRam("Uso Memória Ram"),
    useDisc("Uso Disco")
}
