import androguard
from androguard.misc import AnalyzeAPK
from androguard.core.bytecode import FormatClassToJava

context_classes = ["Landroid/app/Application;", "Landroid/app/Activity;"]

# Returns the superclass name if class object is not external.
def extends(classobj):
	if classobj.external:
		return "Ljava/lang/Object;"
	else:
		return classobj.orig_class.get_superclassname()

# Adds classes that are subclasses of any context class to a list. 
def extendsApplication(d):
	applicationSubClasses = []
	for dex in d:
		classes = dex.get_classes()
		for cls in classes:
			parentClass = cls.get_superclassname()
			if (parentClass in context_classes):
				applicationSubClasses.append(cls.get_name())
	return applicationSubClasses

# Adds classes that are subclasses of any context class to a list. 
def extends(dx):
	applicationSubClasses = []
	for c in list(dx.get_classes()):
		parentClass = extends(c)
		if (parentClass in context_classes):
			applicationSubClasses.append(c.name)
	return applicationSubClasses

def callsGAC(d, subclasses):
	context_fields = []
	for dex in d:
		classes = dex.get_classes()
		for cls in classes:
			class_data = cls.get_class_data()
			if class_data is not None:
				static_fields = class_data.get_static_fields()
				for field in static_fields:
					init_value = field.get_init_value()
					if init_value is not None:
						value = str(init_value.get_value())
						for subclass in subclasses:
							print(value)
							if (subclass.find(value) != -1):
								context_fields.append(field.get_name())		
	return context_fields

def main():
	a, d, dx = AnalyzeAPK('demo.apk')
	subclasses = extendsApplication(d)
	#print(subclasses)
	fields = callsGAC(d, subclasses)
	#print(fields)


if __name__== "__main__":
	main()

# for m in dx.find_methods(classname="Lat/markushi/ui/ActionView;"):
	# 	orig_method = m.get_method()
	# 	print("Found Method --> {}".format(orig_method))

	# 	for other_class, callee, offset in m.get_xref_to():
	# 		print(other_class)
