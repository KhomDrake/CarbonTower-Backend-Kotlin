package com.carbontower.domain.entities.response

import java.math.BigDecimal

data class MachineMetricData(val useRam: BigDecimal,
                             val tempGPU: BigDecimal,
                             val useGPU: BigDecimal,
                             val useCPU: BigDecimal,
                             val useDisc: BigDecimal,
                             val rpmCooler: Int,
                             val tempCPU: BigDecimal,
                             val usbDevice: String,
                             val metricDate: String,
                             val metricTime: String,
                             val idMachine: Int,
                             val idMachineMetric: Int)