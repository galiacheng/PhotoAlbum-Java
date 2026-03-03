# Architecture Diagram

PhotoAlbum is a Spring Boot web application that stores photos as BLOBs in an Oracle Database and serves them via a Thymeleaf-based UI, deployed as a Docker container.

## Application Architecture

```mermaid
flowchart TD
    Browser["Web Browser\nHTTP Client"]

    subgraph Docker["Docker Compose Environment"]
        subgraph App["photoalbum-java-app\nSpring Boot 2.7.18 / Java 8"]
            subgraph Presentation["Presentation Layer"]
                HC["HomeController\nGET / - gallery view\nPOST /upload - upload photos\nDELETE /photo/id - delete photo"]
                DC["DetailController\nGET /detail/id - photo detail\nwith prev/next navigation"]
                PFC["PhotoFileController\nGET /photo/id - serve photo bytes"]
                TH["Thymeleaf Templates\nindex.html, detail.html, layout.html"]
            end

            subgraph Business["Business Logic Layer"]
                PS["PhotoService / PhotoServiceImpl\nFile type and size validation\nImage dimension extraction\nUUID-based filename generation"]
            end

            subgraph DataAccess["Data Access Layer"]
                PR["PhotoRepository\nSpring Data JPA\nOracle-native queries\nROWNUM pagination\nAnalytical functions"]
                JPA["Hibernate ORM\nOracle Dialect\nDDL auto-create"]
            end
        end

        subgraph DB["oracle-db\nOracle Database Free 23ai"]
            PHOTOS["PHOTOS table\nid VARCHAR2\noriginal_file_name\nphoto_data BLOB\nstored_file_name\nfile_path\nfile_size NUMBER\nmime_type\nuploaded_at TIMESTAMP\nwidth / height"]
        end
    end

    Browser -- "HTTP requests port 8080" --> Presentation
    HC -- "upload / delete / list" --> PS
    DC -- "fetch photo detail" --> PS
    PFC -- "fetch photo bytes" --> PS
    TH -- "rendered by" --> HC
    TH -- "rendered by" --> DC
    PS -- "CRUD operations" --> PR
    PR -- "JPA / JDBC ojdbc8" --> JPA
    JPA -- "SQL / BLOB r/w" --> PHOTOS
```
