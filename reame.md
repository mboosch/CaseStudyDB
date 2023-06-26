
# XML-Reader für Zugpläne

Diese in Java und Spring Boot geschriebene Anwendung sucht in XML-Zugplänen nach passenden Gleisabschnitten und gibt für einen entsprechenden Request (bspw. GET /station/FF/train/3210/waggon/10) eine Liste an Gleisabschnitten zurück, die zu Bahnhof, Zug und Waggon passen.

Die XML-Zugpläne müssen im Resources-Ordner liegen und sind zugänglich unter:
https://data.deutschebahn.com/dataset/data-wagenreihungsplan-soll-daten.html
(Wagenreihungsplan Stand 12/2017)