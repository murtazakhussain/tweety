package com.tweety.dependencies;

import com.tweety.TweetyApp;
import com.tweety.code.base.BaseActivity;
import com.tweety.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(TweetyApp taxiApplication);

    void inject(BaseActivity activity);
}
