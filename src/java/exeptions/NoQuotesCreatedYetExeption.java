/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeptions;

/**
 *
 * @author Ismail Cam
 */
public class NoQuotesCreatedYetExeption extends Exception
{

    /**
     * Creates a new instance of <code>NoQuotesCreatedYetExeption</code> without detail message.
     */
    public NoQuotesCreatedYetExeption()
    {
    }

    /**
     * Constructs an instance of <code>NoQuotesCreatedYetExeption</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoQuotesCreatedYetExeption( String msg )
    {
        super( msg );
    }
}
