package fashionlife.com.ui.find.data;

import java.util.List;

import fashionlife.com.base.data.BaseBean;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherBean extends BaseBean {

    /**
     * msg : success
     * result : [{"airCondition":"良","city":"合肥","coldIndex":"低发期","date":"2017-10-24","distrct":"合肥","dressingIndex":"夹衣类","exerciseIndex":"非常适宜","future":[{"date":"2017-10-24","night":"晴","temperature":"13°C","week":"今天","wind":"东风 小于3级"},{"date":"2017-10-25","dayTime":"多云","night":"多云","temperature":"21°C / 13°C","week":"星期三","wind":"东风 小于3级"},{"date":"2017-10-26","dayTime":"多云","night":"晴","temperature":"22°C / 13°C","week":"星期四","wind":"西北风 小于3级"},{"date":"2017-10-27","dayTime":"多云","night":"晴","temperature":"23°C / 12°C","week":"星期五","wind":"北风 小于3级"},{"date":"2017-10-28","dayTime":"晴","night":"晴","temperature":"22°C / 11°C","week":"星期六","wind":"东北风 小于3级"},{"date":"2017-10-29","dayTime":"多云","night":"晴","temperature":"19°C / 9°C","week":"星期日","wind":"东北风 3～4级"},{"date":"2017-10-30","dayTime":"晴","night":"多云","temperature":"15°C / 8°C","week":"星期一","wind":"北风 小于3级"},{"date":"2017-10-31","dayTime":"晴","night":"少云","temperature":"18°C / 11°C","week":"星期二","wind":"东风 2级"}],"humidity":"湿度：54%","pollutionIndex":"79","province":"安徽","sunrise":"06:20","sunset":"17:29","temperature":"17℃","time":"19:21","updateTime":"20171024194515","washIndex":"不太适宜","weather":"晴","week":"周二","wind":"东北风2级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 良
         * city : 合肥
         * coldIndex : 低发期
         * date : 2017-10-24
         * distrct : 合肥
         * dressingIndex : 夹衣类
         * exerciseIndex : 非常适宜
         * future : [{"date":"2017-10-24","night":"晴","temperature":"13°C","week":"今天","wind":"东风 小于3级"},{"date":"2017-10-25","dayTime":"多云","night":"多云","temperature":"21°C / 13°C","week":"星期三","wind":"东风 小于3级"},{"date":"2017-10-26","dayTime":"多云","night":"晴","temperature":"22°C / 13°C","week":"星期四","wind":"西北风 小于3级"},{"date":"2017-10-27","dayTime":"多云","night":"晴","temperature":"23°C / 12°C","week":"星期五","wind":"北风 小于3级"},{"date":"2017-10-28","dayTime":"晴","night":"晴","temperature":"22°C / 11°C","week":"星期六","wind":"东北风 小于3级"},{"date":"2017-10-29","dayTime":"多云","night":"晴","temperature":"19°C / 9°C","week":"星期日","wind":"东北风 3～4级"},{"date":"2017-10-30","dayTime":"晴","night":"多云","temperature":"15°C / 8°C","week":"星期一","wind":"北风 小于3级"},{"date":"2017-10-31","dayTime":"晴","night":"少云","temperature":"18°C / 11°C","week":"星期二","wind":"东风 2级"}]
         * humidity : 湿度：54%
         * pollutionIndex : 79
         * province : 安徽
         * sunrise : 06:20
         * sunset : 17:29
         * temperature : 17℃
         * time : 19:21
         * updateTime : 20171024194515
         * washIndex : 不太适宜
         * weather : 晴
         * week : 周二
         * wind : 东北风2级
         */

        private String airCondition;
        private String city;
        private String coldIndex;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String pollutionIndex;
        private String province;
        private String sunrise;
        private String sunset;
        private String temperature;
        private String time;
        private String updateTime;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class FutureBean {
            /**
             * date : 2017-10-24
             * night : 晴
             * temperature : 13°C
             * week : 今天
             * wind : 东风 小于3级
             * dayTime : 多云
             */

            private String date;
            private String night;
            private String temperature;
            private String week;
            private String wind;
            private String dayTime;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }
        }
    }
}
