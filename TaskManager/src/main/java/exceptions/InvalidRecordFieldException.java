/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author USER
 */
public class InvalidRecordFieldException extends Exception {

    public InvalidRecordFieldException() {
    }

    public InvalidRecordFieldException(String message) {
        super(message);
    }
}
