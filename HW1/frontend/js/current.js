async function getByCity() {
    const city = document.getElementById("city").value;
    const params = new URLSearchParams();
    params.append("city", city);

    fetch(`http://localhost:8080/api/airquality/current_city?${params}`)
        .then(res => {
            return res.json();
        })
        .then(data => {
            console.log(data);
            showData(data);
        })
        .then(
            document.getElementById('city').value = "",
        );

}

async function getByCoords() {
    const lat = document.getElementById("latitude").value;
    const lon = document.getElementById("longitude").value;

    const params = new URLSearchParams();
    params.append("lat", lat);
    params.append("lon", lon);

    fetch(`http://localhost:8080/api/airquality/current_coords?${params}`)
        .then(res => {
            return res.json();
        })
        .then(data => {
            console.log(data);
            showData(data);
        })
        .then(
            document.getElementById("latitude").value = "",
            document.getElementById("longitude").value = "",
        );
}

function showData(data) {
    const cityData = document.getElementById('city-data');
    cityData.innerHTML = `
        <dl>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">City</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.name}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Latitude</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.latitude}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Longitude</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.longitude}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Country</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.country}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Air Quality Index</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.aqi}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Carbon monoxide (CO)</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.ccarbon_monoxide}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Nitrogen dioxide (NO2)</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.cnitrogen_dioxide}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Ozone (O3)</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.cozone}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">Sulphur dioxide (SO2)</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.csulphur_dioxide}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">PM2.5 particulates</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.cpm2_5}</dd>
            </div>
            <div class="bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-6">
                <dt class="mt-1 text-sm font-medium text-gray-500 sm:col-span-2 sm:mt-0">PM10 particulates</dt>
                <dd class="mt-1 text-sm text-gray-900 sm:col-span-2 sm:mt-0">${data.cpm10}</dd>
            </div>
        </dl>
    `
    document.getElementById("city-data").scrollIntoView({ behavior: 'smooth' });
}