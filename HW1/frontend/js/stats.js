async function getStats() {
    fetch(`http://localhost:8080/api/cache/stats`)
        .then(res => {
            return res.json();
        })
        .then(data => {
            console.log(data);
            showStats(data);
        })
}

function showStats(data) {
    const cacheData = document.getElementById('cache-stats');
    cacheData.innerHTML = `
        <dl class="grid grid-cols-1 gap-4 sm:grid-cols-3">
            <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
                <dt class="order-last text-lg font-medium text-gray-500"> Requests </dt>
                <dd class="text-4xl font-extrabold text-blue-600 md:text-5xl">${data.Requests}</dd>
            </div>
            <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
                <dt class="order-last text-lg font-medium text-gray-500"> Hits </dt>
                <dd class="text-4xl font-extrabold text-blue-600 md:text-5xl">${data.Hits}</dd>
            </div>
            <div class="flex flex-col rounded-lg border border-gray-100 px-4 py-8 text-center">
                <dt class="order-last text-lg font-medium text-gray-500"> Misses </dt>
                <dd class="text-4xl font-extrabold text-blue-600 md:text-5xl">${data.Misses}</dd>
            </div>
        </dl>
    `
}