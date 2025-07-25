package app.resume

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(packages = ["app.resume"], importOptions = [ImportOption.DoNotIncludeTests::class]) // 테스트 패키지는 아키텍처 테스트에서 제외
class HexagonalArchitectureTest {
    @ArchTest
    fun hexagonalArchitecture(classes: JavaClasses) {
        Architectures.layeredArchitecture()
            .consideringAllDependencies()
            .layer("domain").definedBy("app.resume.domain..")
            .layer("application").definedBy("app.resume.application..")
            .layer("adapter").definedBy("app.resume.adapter..")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "adapter")
            .whereLayer("application").mayOnlyBeAccessedByLayers("adapter")
            .whereLayer("adapter").mayNotBeAccessedByAnyLayer()
            .check(classes)
    }
}