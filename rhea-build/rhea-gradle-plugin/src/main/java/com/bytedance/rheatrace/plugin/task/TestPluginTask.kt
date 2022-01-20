package com.bytedance.rheatrace.plugin.task

import com.bytedance.rheatrace.plugin.internal.common.RheaLog
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.internal.TaskOutputsInternal
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import java.io.File

abstract class TestPluginTask : DefaultTask() {
    @get:Incremental
    @get:InputFiles
    abstract val inputDir: ConfigurableFileCollection

    @get:OutputDirectory
    abstract val outputDirectory: RegularFileProperty

    init {
        outputDirectory.set(File(project.buildDir.absolutePath, "test"))
    }

    @TaskAction
    fun execute(inputChanges: InputChanges) {
        inputChanges.getFileChanges(inputDir).forEach { change ->
            RheaLog.e("TestPluginTask", "inputChanges  ${change.file}")

        }

        RheaLog.e("TestPluginTask", "TestPluginTask  $inputDir")
        RheaLog.i(
            "TestPluginTask",
            "Generating settings: (incremental: ${inputChanges.isIncremental})"
        )
    }

    override fun getOutputs(): TaskOutputsInternal {
        return super.getOutputs()
    }
}