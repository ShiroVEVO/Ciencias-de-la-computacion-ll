$directorioActual = Get-Location

if ($directorioActual -ne $null) {
    Set-Location -Path $directorioActual
    Write-Host "[CIENCIAS II] El directorio del script es: $directorioActual"

    $nombreArchivo = "servidorWeb.mjs"
    $rutaArchivo = Join-Path -Path $directorioActual -ChildPath $nombreArchivo

    if (Test-Path -Path $rutaArchivo -PathType Leaf) {

        Write-Host "[CIENCIAS II] El archivo $nombreArchivo existe en el directorio."

        $comando = "node servidorWeb.mjs"
        Invoke-Expression -Command $comando

    } else {
        Write-Host "[ERROR] El archivo $nombreArchivo no existe en el directorio."
    }

} else {
    Write-Host "[ERROR] No se pudo determinar el directorio del script."
}