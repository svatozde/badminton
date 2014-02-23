/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.testRules;

import cz.cvut.kbss.wpa.exceptions.AbstractServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author zdeněk
 */
public class WPAExceptionRule implements TestRule {

    private static WPAExceptionRule instance;

    public static WPAExceptionRule getInstance() {
        if (instance == null) {
            instance = new WPAExceptionRule();
        }
        return instance;
    }

    private WPAExceptionRule() {
    }

    @Override
    public Statement apply(Statement stmnt, Description d) {
        ExpectedCode e = d.getAnnotation(ExpectedCode.class);
        if (e == null) {
            return stmnt;
        } else {
            return new CheckCodeStatement(e, stmnt);
        }
    }

    private class CheckCodeStatement extends Statement {

        Statement original;
        ExceptionCodes code;
        Class<?> clazz;

        public CheckCodeStatement(ExpectedCode e, Statement o) {
            code = e.code();
            clazz = e.exceptionClass();
            original = o;
        }

        @Override
        public void evaluate() throws Throwable {
            try {
                original.evaluate();
                fail("Exception " + clazz.getName() + " with code " + code + " was expected");
            } catch (Throwable t) {
                if (t.getClass().equals(clazz)) {
                    AbstractServiceException ex = (AbstractServiceException) t;
                    Assert.assertEquals("Expected code was " + code + " but get " + ex.getCode(), code, ex.getCode());
                } else {
                    throw t;
                }

            }
        }
    }

}
