

////// PRPO

VSAK entity bean ima samo svoje CRUD metode ostale metode pa so impementirane v poslovnihBeanih tam so poslovne metode.


usvtariš DTO objekte, za prenašanje podatkov, npr.. agregiranih podatkov iz poslovnih metod!


DTO objekte podajamo kot argumente poslovnim metodam??? Primer DTOja si slikal!


Dodaj eno request scope in eno application scope in sproti loggiraj UUID... ko večkrat kličemo application scoped more logger vedno zapisovat isti UUID pri requestu pa nekaj drugega1!!!!!

commitaj spremembe v stari repo!!

/// functions
* TODO Uporabnik lahko doda novo sliko
* TODO Uporabnik lahko doda sliko obstoječemu albumu
* TODO Uporabnik lahko doda več novih slik
* TODO Uporabnik lahko doda kategorijo
* TODO Uporabnik lahko nastavi kategorijo sliki/am, če ne dobi nastavitev default











RSO ///////////



docker run -d -p 2379:2379 --name etcd --volume=/tmp/etcd-data:/etcd-data quay.io/coreos/etcd:latest  /usr/local/bin/etcd --name my-etcd-1 --data-dir /etcd-data --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://0.0.0.0:2379 --listen-peer-urls http://0.0.0.0:2380 --initial-advertise-peer-urls http://0.0.0.0:2380 --initial-cluster my-etcd-1=http://0.0.0.0:2380    --initial-cluster-token my-etcd-token --initial-cluster-state new  --auto-compaction-retention 1 -cors="*"


//Okoljske spremenljivke


- Album recimo če je konfiguracija naj izpisuje brez kategorij... Samo da pokažeš da deluje
- Maintanence mode


//Povezovanje
Vsak container ima svoj interface--- lahko ima svoja IP naslov

Lahko ppovemo da se neka storitev poveže na IP enega containerja. To ni OK rešitev ker če ga ugasnemo ni nujno da bo imel enak IP.

Docker ima s tem vgrajen mehanizem za odkrivanje containerjav. Ustvarimo navidezno mrežo. Dva containerja zaženemo v tem omrežju. In tam samo rečemo da se povežemo na imeServica:port in on bo to resolvala pravilno!

Docker network RSO 
Docker run servie --network RSO (zažene service v tem networku) in potem se stvar tam zažene. Tam notri deluje resolvanje 


//Service discovery
Postavi etcd server
Projektu dodaj KumuluzEE Discovery
 - poglej rešen primer -> link na nalogi 3

1. request na service discovery, kjer dobimo url z etcd
2. kličemo storitev ki smo ga prejeli



1. korak: docker start etcd

2. korak: docker start rso-order    rso-customer je bil pomojem


etcd
ENV
config.yml
system properties


Koraki implementacije
v customer-services.pom dodamo 
kumu-config-etcd dependency


configyaml -> povemo kje se laufa etcd

etcd zaženi iz kot je napisano v readme.md
probaj drugače zagnat


@ConfigBudle

AppProperties.class
@ConfigValue v nekem zrnu definiramo


to potem dostopamo v logiki do tega classa in preverimo vrednost valua




Service discovery
na aplikacijo daj @RegisterService  -> ""/v1


Customers bean!!!
@Inject
@DiscoverService("rso-orders")
private Optional<String> baseUrl



-----
etcd nima nobenega UIja, obstaja edino nek command tool kjer lahko beremo in spreminjamo

UI-> henszey.github.io/etcd-browser



zaženemo še drugo storitev... ob initu storitve piše da se je povezala in registrirala

to lahko tudi vidimo na UI od etcdja.



nek request je poslal kjer se potem vidi da bo neko vrednost etcd povedal če pride do spremembe???



Ko se izvede request tistega beana, se property zapiše v log in si s tem lahko pomagamo da ne sestavlamo celega imena!


spremenimo nastavitev, pošljemo request in takrat se sprememba pozna!!!! 

Za nalogo naredi nek demo, kjer lahko pokažaemo na zagovoru!

Cilj nalge-> spremeba nastavitve se odraža lokalno in da se stvar service discovery nardi!!!
ostalo pa premisli in odgovori na vprašanja!!!


Konec novembra bo zagovor nalog... dve delujoči storitvi deployani v cloudu!



V Dockerfile -> docker compose... določiš kater network posluša oziroma preko katerega je zagnan! ali pač?

docker-compose.yml je file!!!







Vprašanja!!
 - Kako prejeti binarno datoteko v POST?
 - Ali imamo lahko Application in Servlet? Je to isto? Lahko ja... AX






 appProperties.isExternalServiceEnabled() kot testno kličeš tolk da vidš kam gre iskat... lahko tut celo pot kopiramo v root na etcd browserju in ustvari tudi boolean

-->>>> spodaj je log na config server
 Initialization of bean...
2018-10-30 12:49:17.967 INFO -- com.kumuluz.ee.config.etcd.Etcd2ConfigurationSource -- Initializing watch for key: environments/dev/services/catalog-services/1.0.0/config/app-properties/external-services/enabled
2018-10-30 12:49:17.971 INFO -- com.socialbook.catalogs.coreServices.AlbumsBean -- Getting album with id: 1
2018-10-30 12:50:54.702 INFO -- com.kumuluz.ee.config.etcd.Etcd2ConfigurationSource -- Value changed. Key: app-properties.external-services.enabled New value: true
2018-10-30 12:50:54.702 INFO -- com.kumuluz.ee.config.etcd.Etcd2ConfigurationSource -- Initializing watch for key: environments/dev/services/catalog-services/1.0.0/config/app-properties/external-services/enabled









1. zaženi z dolgim ukazom etcd
2. zaženi aplikacijo
3. dodaj config v etcd 
4. ob spremembi se bi moral log pokazat!



docker-compose up -> to zažene ta docker compse .yml




















////PRPO


pri POST vrni status.Created!


pri requestih v postmanu morajo biti headerji ki določajo text/json


---interceptor


@BeleziKlice
public static getCustomers() {
	//belezi klic se izvede ob začetku getCustomers metode!

}

Koraki
1.definiramo anotacijo
2.definiramo kodo ki se izvede ko kličemo nekej
3.v zrnu se naj povečju števec
4.to anotacijo je potrebno registirat



na enititete daj anotacijo @JsonbTransient







