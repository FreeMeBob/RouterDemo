package com.kika.routerDemo;

import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Set;

/**
 * Created by xinmei on 2018/1/31.
 */

public class DirectoryOperator extends ClassVisitor implements Opcodes {

    boolean trigger = false;

    public DirectoryOperator(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("************ visitAnnotation: desc=" + desc + " visible= " + visible + " *************");
        if (desc.contains("Module")) {
            trigger = true;
        }
        return this.cv != null ? this.cv.visitAnnotation(desc, visible) : null;
    }

    @Override
    public MethodVisitor visitMethod(int access, final String name, final String desc, String signature,
                                     String[] exceptions) {

        if (trigger) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            return mv == null ? null : new MethodAdapter(mv, access, name, desc);
        } else {
            return this.cv != null ? this.cv.visitMethod(access, name, desc, signature, exceptions) : null;
        }
    }


}
