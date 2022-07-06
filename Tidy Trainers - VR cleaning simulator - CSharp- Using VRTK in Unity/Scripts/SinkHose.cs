/// <summary>
/// Aziz
/// this script controls joints connected to faucet
/// </summary>
namespace VRTK.Examples
{
    using UnityEngine;
   
    public class SinkHose : MonoBehaviour
    {

        protected Rigidbody[] lampRigidbodies = new Rigidbody[0];
        public VRTK_InteractableObject linkedObject;
        [SerializeField] Transform hosePos;
        Rigidbody m_Rigidbody;
        bool resetPos = false;

        private void Start()
        {
            m_Rigidbody = GetComponent<Rigidbody>();
        }
        protected virtual void OnEnable()
        {
            linkedObject = (linkedObject == null ? GetComponent<VRTK_InteractableObject>() : linkedObject);

            if (linkedObject != null)
            {
                linkedObject.InteractableObjectGrabbed += InteractableObjectGrabbed;
                linkedObject.InteractableObjectUngrabbed += InteractableObjectUngrabbed;
            }

            lampRigidbodies = transform.parent.GetComponentsInChildren<Rigidbody>();
        }

        protected virtual void OnDisable()
        {
            if (linkedObject != null)
            {
                linkedObject.InteractableObjectGrabbed -= InteractableObjectGrabbed;
                linkedObject.InteractableObjectUngrabbed -= InteractableObjectUngrabbed;
            }
        }

        protected virtual void InteractableObjectUngrabbed(object sender, InteractableObjectEventArgs e)
        {
            ToggleKinematics(false);
            m_Rigidbody.constraints = RigidbodyConstraints.FreezeAll;
            hosePos.transform.position += Vector3.down * 2f;
            resetPos = true;
        }

        protected virtual void InteractableObjectGrabbed(object sender, InteractableObjectEventArgs e)
        {
            ToggleKinematics(false);
            if (resetPos) // forbids moving hose in the first grab, it offsets it on ungrab then brings it back on grab
            {
                m_Rigidbody.constraints = RigidbodyConstraints.None;
                hosePos.transform.position += Vector3.up * 2f;
            }
  

        }

        protected virtual void ToggleKinematics(bool state)
        {
            foreach (Rigidbody elementRigidbody in lampRigidbodies)
            {
                elementRigidbody.isKinematic = state;

            }
        }
       
    }
}