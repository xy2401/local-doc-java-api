/*
 * Copyright (c) 1998, 1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */
package examples.activation; 

import java.rmi.*;
import java.rmi.activation.*;

public class MyNonRemoteClassAdapter implements examples.activation.AnotherRemoteInterface {

    private String result = null;
    private MyNonRemoteClass mnrc;

    // The constructor for activation and export; this constructor is
    // called by the method ActivationInstantiator.newInstance during
    // activation, to construct the object.
    //
    public MyNonRemoteClassAdapter(ActivationID id, MarshalledObject data) 
        throws RemoteException {

        // Register the object with the activation system
        // then export it on an anonymous port
        //
        Activatable.exportObject(this, id, 0);

        // Create an instance of the class MyNonRemoteClass
	//
        mnrc = new MyNonRemoteClass();
    }

    // Define the method declared in AnotherRemoteInterface
    // to accept a String, modify it, and return it to the client
    //
    public String calltheServer(String takeThis) throws RemoteException {

	// Rather than modify the String here, forward
	// it on to the non-remote object impleemntation
	//
        result = mnrc.calltheServer(takeThis);
        return result;
    }

}
