package example;

import org.argus.amandroid.alir.componentSummary.ApkYard;
import org.argus.amandroid.core.appInfo.ApkCertificate;
import org.argus.amandroid.core.decompile.DecompileLayout;
import org.argus.amandroid.core.decompile.DecompileStrategy;
import org.argus.amandroid.core.decompile.DecompilerSettings;
import org.argus.amandroid.core.parser.ComponentInfo;
import org.argus.amandroid.core.parser.IntentFilterDataBase;
import org.argus.amandroid.core.ApkGlobal;
import org.argus.jawa.core.DefaultReporter;
import org.argus.jawa.core.JawaType;
import org.argus.jawa.core.Signature;
import org.argus.amandroid.core.AndroidConstants;
import org.argus.jawa.core.DefaultLibraryAPISummary;
import org.argus.jawa.core.util.FileUtil;
import scala.collection.immutable.Set;
import scala.collection.immutable.Map;
import scala.Tuple2;

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

        String appName = apk.model().getAppName();
        Set<ApkCertificate> certificates = apk.model().getCertificates();
        Set<String> uses_permissions = apk.model().getUsesPermissions();
        Set<ComponentInfo> component_infos = apk.model().getComponentInfos();
        IntentFilterDataBase intent_filter = apk.model().getIntentFilterDB();
        Map<JawaType, Tuple2<Signature, String>> environment_map = apk.model().getEnvMap();
    }
}
