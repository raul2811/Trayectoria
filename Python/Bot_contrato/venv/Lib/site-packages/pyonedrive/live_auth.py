""" Convenience wrapper to handle Oauth authentication

"""
import requests


class LiveAuth(object):
    """ Live Oauth authentication helper

    """
    _base_url = 'https://login.live.com/'
    _authorize_uri = 'oauth20_authorize.srf'
    _token_url = 'oauth20_token.srf'

    def __init__(self, client_id, client_secret, scope, redirect_uri):
        self._client_id = client_id
        self._client_secret = client_secret
        self._scope = scope
        self._redirect_uri = redirect_uri

    def generate_oauth_initiation_url(self, response_type):
        """ generate the oauth dialog initiation url

        The user must be redirected to the obtained url

        @param response_type: the type of response to ask for the server,
        can be 'token' for implicit grant flow or 'code' for Authorization code
        grant flow
        @return: the url to redirect the user to
        @rtype: str
        """
        if response_type != 'code' and response_type != 'token':
            raise ValueError("response_type must be 'code' or 'token'")
        return '{base}{authorize}?client_id={id}&scope={scope}&' \
               'response_type={type}&redirect_uri={redirect}' \
            .format(base=self._base_url,
                    authorize=self._authorize_uri,
                    id=self._client_id,
                    scope=self._scope,
                    type=response_type,
                    redirect=self._redirect_uri)

    def exchange_oauth_code_for_token(self, code):
        """ Call Live API to exchange a authentication code for token(s)

        If the asked response_type for oauth authentication

        @param code: authentication coe
        @return: API's raw response
        @rtype: Response
        """
        post_data = {
            'client_id': self._client_id,
            'client_secret': self._client_secret,
            'redirect_uri': self._redirect_uri,
            'grant_type': 'authorization_code',
            'code': code
        }
        return requests.post('{base}{token}'.format(
            base=self._base_url, token=self._token_url),
                             data=post_data)