import androguard
from androguard.misc import AnalyzeAPK
from androguard.core.bytecode import FormatClassToJava

def extends(classobj):
	if classobj.external:
		return "Ljava/lang/Object;"
	else:
		return classobj.orig_class.get_superclassname()

# Adds classes to a list that extend application. 
def extendsApplication(dx):
	applicationSubClasses = []
	for c in list(dx.get_classes()):
		parentClass = extends(c)
		if (parentClass == "Landroid/app/Application;"):
			applicationSubClasses.append(c.name)
	return applicationSubClasses


a, d, dx = AnalyzeAPK('example.apk')
subClasses = extendsApplication(dx)
print(subClasses)

# for m in dx.find_methods(classname="Lat/markushi/ui/ActionView;"):
	# 	orig_method = m.get_method()
	# 	print("Found Method --> {}".format(orig_method))

	# 	for other_class, callee, offset in m.get_xref_to():
	# 		print(other_class)
