package com.kika.routerDemo;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by xinmei on 2018/1/31.
 */

public class MethodAdapter extends AdviceAdapter implements Opcodes {

    protected boolean methodTrigger = false;

    protected MethodAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM5, mv, access, name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (desc.contains("AutoWire")) {
            methodTrigger = true;
        }
        return this.mv != null ? this.mv.visitAnnotation(desc, visible) : null;
    }

    @Override
    protected void onMethodEnter() {
        if (methodTrigger) {
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("This is a test");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }
}
