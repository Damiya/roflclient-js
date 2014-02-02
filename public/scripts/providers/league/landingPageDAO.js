/*
 * Copyright 2014 Kate von Roeder (katevonroder at gmail dot com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';

angular.module('legendary')
  .factory('landingPageDAO', ['$rootScope', 'RestangularFactory', 'sessionStorage', '$q', function ($rootScope, RestangularFactory, sessionStorage, $q) {
    var factory = {
      nextGameListUpdate: 0,

      getLandingPageContent: function () {
        var deferred = $q.defer();
        var landingPageContent = sessionStorage.getItem('landingPageContent');
        if (!landingPageContent) {
          RestangularFactory.league.one('landingPage').get().then(function (response) {
            sessionStorage.setItem('landingPageContent', response.originalElement);

            deferred.resolve(response.originalElement);
          });
        } else {
          deferred.resolve(landingPageContent);
        }

        return deferred.promise;
      },

      getGameList: function () {
        var deferred = $q.defer();
        var now = new Date().getTime();
        var gameList = sessionStorage.getItem('gameList');

        if (!gameList || factory.nextGameListUpdate <= now) {
          RestangularFactory.league.one('featuredGames').get().then(function (response) {
            sessionStorage.setItem('gameList', response.gameList);
            factory.nextGameListUpdate = now + response.clientRefreshInterval;

            deferred.resolve(response.gameList);
          });
        } else {
          deferred.resolve(gameList);
        }

        return deferred.promise;
      }
    };

    return factory;
  }]);