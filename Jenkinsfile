@Library('bizapps-global-lib') _

bizappUiTestPipeline {
    projectName = 'bizap-aus-analyzer-tests'
    rpCfg.project = 'aus'

    parallelRest()
    forceRestAndUiParallel = true

    branchesToExecuteTestByDefault = ['master']

    javaVersion = 11
}
