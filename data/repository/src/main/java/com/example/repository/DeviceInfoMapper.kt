package com.example.repository

import com.example.db.entity.DeviceInfoBoxEntity
import com.example.model.DeviceInfoBox

internal fun List<DeviceInfoBoxEntity>.toModel() = map { it.toModel() }

//internal Module内のみ

internal fun DeviceInfoBoxEntity.toModel() =
    DeviceInfoBox(
        id = id,
        name = name,
        maker = maker,
        os = os,
        displaySize = displaySize,
        dateModified = dateModified,
        dateAdded = dateAdded
    )

internal fun DeviceInfoBox.toEntity () =
    DeviceInfoBoxEntity(
        id = id,
        name = name,
        maker = maker,
        os = os,
        displaySize = displaySize,
        dateModified = dateModified,
        dateAdded = dateAdded
    )