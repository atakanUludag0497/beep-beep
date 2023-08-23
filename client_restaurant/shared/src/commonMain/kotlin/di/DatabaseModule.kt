package di

import data.remote.model.TokenDto
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        RealmConfiguration.Builder(
            schema = setOf(TokenDto::class)
        ).compactOnLaunch().build()
    }
    single { Realm.open(configuration = get<RealmConfiguration>()) }
}