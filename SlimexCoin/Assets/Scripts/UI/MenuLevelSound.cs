using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MenuLevelSound : MonoBehaviour
{
    [SerializeField] AudioSource music;

    // Start is called before the first frame update
    void Start()
    {
        if (OnOffMusic.isOn == false)
        {
            Destroy(this.gameObject);
        }
    }
}
