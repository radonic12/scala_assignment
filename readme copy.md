# Arkitektur

Til database strukturen vil jeg blot flade features objected ud, og lave en partition på properties.parameterId, da opgaven ligger op til at der kun laves udregning og summering indenfor et bestemt parameterId f.eks. vindhastighed. Derved vil databasen kun skulle lave beregningner indenfor den bestemte partition, i stedet for hele databasen. 

Database tabellen vil have denne struktur

| Navn        | Type         | Json_path               |
| ----------- | ------------ | ----------------------- |
| longitude   | Decimal      | geometry.coordinates[0] |
| latitude    | Decimal      | geometry.coordinates[1] |
| id          | UUID         | id                      |
| type        | varchar(30)  | type                    |
| created     | DateTime     | properties.created      |
| observed    | DateTime     | properties.observed     |
| parameterId | Varchar(100) | properties.parameterId  |
| stationId   | Varchar(20)  | properties.stationId    |
| value       | Decimal      | properties.value        |
