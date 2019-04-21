package example;

import org.argus.amandroid.alir.componentSummary.ApkYard;
import org.argus.amandroid.core.decompile.DecompileLayout;
import org.argus.amandroid.core.decompile.DecompileStrategy;
import org.argus.amandroid.core.decompile.DecompilerSettings;
import org.argus.amandroid.core.ApkGlobal;
import org.argus.jawa.core.DefaultReporter;
import org.argus.amandroid.core.AndroidConstants;
import org.argus.jawa.core.DefaultLibraryAPISummary;
import org.argus.jawa.core.util.FileUtil;

class LoadApk {
    public static void main(String[] args){
        String fileUri = FileUtil.toUri(args[0]);
        String outputUri = FileUtil.toUri(args[1]);
        DefaultReporter reporter = new DefaultReporter();
        // Yard is the apks manager
        ApkYard yard = new ApkYard(reporter);
        DecompileLayout layout = new DecompileLayout(outputUri, true, "src", "lib", true);

        // create temporary object to get access to default param methods
        DecompileStrategy tempStrategy = new DecompileStrategy(null, null, null, null, false);
        DecompileStrategy strategy = new DecompileStrategy(layout,
                                                           // these are methods to get the scala
                                                           // default parameter values
                                                           tempStrategy.$lessinit$greater$default$2(),
                                                           tempStrategy.$lessinit$greater$default$3(),
                                                           tempStrategy.$lessinit$greater$default$4(),
                                                           tempStrategy.$lessinit$greater$default$5());
        DecompilerSettings tempSettings = new DecompilerSettings(false, false, null, null, null, 0, null);
        DecompilerSettings settings = new DecompilerSettings(false,
                                                             true,
                                                             strategy,
                                                             reporter,
                                                             tempSettings.$lessinit$greater$default$5(),
                                                             tempSettings.$lessinit$greater$default$6(),
                                                             tempSettings.$lessinit$greater$default$7());

        ApkGlobal apk = yard.loadApk(fileUri, settings, false, false, yard.loadApk$default$5());
    }
}
/* TRANSLATING FROM:

class LoadApkScala {
  def main(args: Array[String]): Unit = {
    if(args.length != 2) {
      println("usage: apk_path output_path")
      return
    }
    val fileUri = FileUtil.toUri(args(0))
    val outputUri = FileUtil.toUri(args(1))
    val reporter = new DefaultReporter
    // Yard is the apks manager
    val yard = new ApkYard(reporter)
    val layout = DecompileLayout(outputUri)
    val strategy = DecompileStrategy(layout)
    val settings = DecompilerSettings(debugMode = false, forceDelete = true, strategy, reporter)
    // apk is the apk meta data manager, class loader and class manager
    val apk = yard.loadApk(fileUri, settings, collectInfo = false, resolveCallBack = false)

    val appName = apk.model.getAppName
    val certificate = apk.model.getCertificates
    val uses_permissions = apk.model.getUsesPermissions
    val component_infos = apk.model.getComponentInfos // ComponentInfo(compType: [class type], typ: [ACTIVITY, SERVICE, RECEIVER, PROVIDER], exported: Boolean, enabled: Boolean, permission: ISet[String])
    val intent_filter = apk.model.getIntentFilterDB // IntentFilterDB contains intent filter information for each component.
    val environment_map = apk.model.getEnvMap // environment method map
  }
}
 */
