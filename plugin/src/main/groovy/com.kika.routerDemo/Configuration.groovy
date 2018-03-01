package com.kika.routerDemo

class Configuration {

    private String className
    private String method
    private String params

    void start(ObjectList objectList) {
        if (objectList.mObjectList != null && objectList.mObjectList.length() > 0) {
            System.out.println("Start load config file {$objectList.mObjectList}")
            def file = new File(objectList.mObjectList)
            if (file.exists()) {
                FileReader reader = new FileReader(file)
                def lines = reader.readLines()
                for (def line : lines) {
                    System.out.println("line:" + line)
                    if (!(line.length() == 0 || line.isEmpty())) {
                        def parts = line.split("-")
                        className = parts[0]
                        method = parts[1]
                        params = parts[2]
                    }
                }
                reader.close()
            }
        }
    }

    String getClassName() {
        return className
    }

    String getMethod() {
        return method
    }

    String getParams() {

        return params
    }
}