@echo off
start cmd /k php C:\Users\MSI\OneDrive\Bureau\PIDEV_IRON_FIT-IntegrationYoussef\bin\console messenger:consume async --time-limit=3600
start cmd /k symfony serve