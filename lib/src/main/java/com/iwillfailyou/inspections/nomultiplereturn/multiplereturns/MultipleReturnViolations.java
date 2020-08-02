package com.iwillfailyou.inspections.nomultiplereturn.multiplereturns;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.iwillfailyou.inspections.allpublic.nonpublics.JavaNonpublic;
import com.iwillfailyou.inspections.allpublic.nonpublics.Nonpublic;
import org.cactoos.BiFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MultipleReturnViolations implements BiFunc<CompilationUnit, TypeDeclaration<?>, List<MultipleReturn>> {

    @Override
    public List<MultipleReturn> apply(
        final CompilationUnit unit,
        final TypeDeclaration<?> root
    ) {
        final List<MultipleReturn> multipleReturns = new ArrayList<>();
        final List<MethodDeclaration> methods = unit.findAll(
            MethodDeclaration.class
        );
        for (final MethodDeclaration method : methods) {
            final List<ReturnStmt> returnStmts = method.findAll(ReturnStmt.class);
            if (returnStmts.size() > 1) {
                multipleReturns.add(new JavaMultipleReturn(method, root));
            }
        }
        return multipleReturns;
    }
}
