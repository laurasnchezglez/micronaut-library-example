package example.security;

import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    /*Aquest provider es farà servir a cada método que estigui annotat amb @Secured
    * D'aquesta manera, nomes deixarà entrar les crides rest amb basicAuth (admin, admin)*/
    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if ( authenticationRequest.getIdentity().equals("admin") &&
                authenticationRequest.getSecret().equals("admin") ) {
            return Flowable.just(new UserDetails((String) authenticationRequest.getIdentity(),
                    new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
    }
}